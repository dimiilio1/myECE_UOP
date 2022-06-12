package net.dimitrisiliou.myhmmy.news;

;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import net.dimitrisiliou.myhmmy.R;
import net.dimitrisiliou.myhmmy.database.DataBaseHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class NewsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private NewsAdapter mAdapter;
    private ArrayList<HashMap<String, String>> resultItems = new ArrayList<>();
    private static String mFeedUrl = "https://www.ece.uop.gr/announcement/feed/";
    NewsModel newsModel;
    DataBaseHelper dataBaseHelper;
    private ProgressDialog progressDialog;
    private boolean isLoading;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView =  inflater.inflate(R.layout.fragment_news, container, false);

        getDataFromWeb();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = getView().findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataBaseHelper = new DataBaseHelper(getContext());
        mAdapter = new NewsAdapter(getContext(), resultItems);
        mRecyclerView.setAdapter(mAdapter);
    }

    public class GetRssClass extends AsyncTask<Void, Void, ArrayList<HashMap<String, String>>> {
        String mUrl;

        public GetRssClass(String url) {
            this.mUrl = url;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected ArrayList<HashMap<String, String>> doInBackground(Void... voids) {
            ArrayList<HashMap<String, String>> result = new ArrayList<>();
            try {
                URL url = new URL(mUrl);

                URLConnection connection = (URLConnection) url.openConnection();
               // connection.setRequestMethod("GET");
                connection.setConnectTimeout(10000);
                connection.setDoInput( true );
                    InputStream inputStream = connection.getInputStream();
                    result = parseXML(inputStream);

            } catch (Exception e) {
                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw));
                    String errors = sw.toString();
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{"support@dimitrisiliou.atlassian.net"});
                    email.putExtra(Intent.EXTRA_SUBJECT, "[myhmmy] ParseException Error Code 003");
                    email.putExtra(Intent.EXTRA_TEXT, errors);
                    email.setType("message/rfc822");
                    startActivity(email);
                    e.printStackTrace();
                Log.d("Exception", e.getMessage());
                return null;
            }
            return result;
        }


        @Override
        protected void onPostExecute(ArrayList<HashMap<String, String>> result) {
            super.onPostExecute(result);
            hideProgressDialog();
            if (result == null) {
                showToast("Please check your connection and try again.");
            }

                int before = resultItems.size();
                resultItems.clear();
                ArrayList<HashMap<String, String>> everyone = dataBaseHelper.getAllRss();
                dataBaseHelper.changeSetting("NEWS_COUNTER", String.valueOf(everyone.size())); //adds the complete number of items that exists in the database
                resultItems.addAll(everyone);
                mAdapter.notifyItemRangeInserted(before, everyone.size());
                mRecyclerView.invalidate();

        }

        private ArrayList<HashMap<String, String>> parseXML(InputStream inputStream)
                throws ParserConfigurationException, IOException, SAXException, ParseException  {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            Element element = document.getDocumentElement();

            NodeList itemlist = element.getElementsByTagName("item");
            NodeList itemChildren;

            Node currentItem;
            Node currentChild;

            String link =null;
            String title = null;
            String pubDate = null;
            String content = null;
            int newsCounter= 0;


            ArrayList<HashMap<String, String>> items = new ArrayList<>(); //the one that returns at the end of the execution
            HashMap<String, String> currentMap; //supported hashmap

            for (int i = itemlist.getLength() -1  ; i > -1 ; i--) { //for each article
                try {
                    currentItem = itemlist.item(i);
                    itemChildren = currentItem.getChildNodes();
                    currentMap = new HashMap<>();

                    for (int j = 0; j < itemChildren.getLength(); j++) { //for each tag inside the article
                        currentChild = itemChildren.item(j);

                        if (currentChild.getNodeName().equalsIgnoreCase("title")) {
                            // Log.d("Title", String.valueOf(currentChild.getTextContent()));
                            currentMap.put("title", currentChild.getTextContent());
                            title = (String) currentChild.getTextContent();
                        }
                        if (currentChild.getNodeName().equalsIgnoreCase("content:encoded")) { //content for a future use
                            // Log.d("description", String.valueOf(currentChild.getTextContent()));
                            currentMap.put("content:encoded", currentChild.getTextContent());
                            content = (String) currentChild.getTextContent();
                        }
                        if (currentChild.getNodeName().equalsIgnoreCase("pubDate")) {
                            // Log.d("Title", String.valueOf(currentChild.getTextContent()));
                            currentMap.put("pubDate", currentChild.getTextContent());
                            pubDate = (String) currentChild.getTextContent();

                        }
                        if (currentChild.getNodeName().equalsIgnoreCase("link")) {
                            currentMap.put("link", currentChild.getTextContent());
                            link = (String) currentChild.getTextContent();
                        }
                    }
                    if (!currentMap.isEmpty()) {
                        items.add(currentMap);
                        newsModel = new NewsModel(link, title, pubDate, content, newsCounter); //category for a future use
                        dataBaseHelper.addOne(newsModel); //adds each new item in the database
                    }

                    ArrayList<HashMap<String, String>> newsArray = dataBaseHelper.getAllRss(); //gets all the new articles from the database
                    newsCounter = newsArray.size(); // gets the size of that array
                }catch (Exception e){
                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw));
                    String errors = sw.toString();
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{"support@dimitrisiliou.atlassian.net"});
                    email.putExtra(Intent.EXTRA_SUBJECT, "[myhmmy] ParseException Error Code 001");
                    email.putExtra(Intent.EXTRA_TEXT, errors);
                    email.setType("message/rfc822");
                    startActivity(email);
                    e.printStackTrace();
                }
            }
            return items;
        }
    }

    private void showProgressDialog() {
        isLoading = true;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Ανανέωση");
        progressDialog.show();
    }

    private void hideProgressDialog() {
        isLoading = false;
        try {
            if ((this.progressDialog != null) && this.progressDialog.isShowing()) {
                this.progressDialog.cancel();
            }else if ((this.progressDialog != null)){
                this.progressDialog.dismiss();
            }
        } catch (final IllegalArgumentException e) {
            // Handle or log or ignore
        } catch (final Exception e) {
            // Handle or log or ignore
        } finally {
            this.progressDialog = null;
        }
    }

    public void getDataFromWeb() {
        GetRssClass fetchRss = new GetRssClass(mFeedUrl);
        fetchRss.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }}

