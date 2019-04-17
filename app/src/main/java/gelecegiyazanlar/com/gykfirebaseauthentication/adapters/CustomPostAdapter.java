package gelecegiyazanlar.com.gykfirebaseauthentication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.PipedOutputStream;
import java.util.List;

import gelecegiyazanlar.com.gykfirebaseauthentication.R;
import gelecegiyazanlar.com.gykfirebaseauthentication.models.PostModel;

public class CustomPostAdapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    List<PostModel> postModelList;


    public CustomPostAdapter(LayoutInflater layoutInflater, List<PostModel> postModelList) {
        this.layoutInflater = layoutInflater;
        this.postModelList = postModelList;
    }


    @Override
    public int getCount() {
        return postModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return postModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View postView = layoutInflater.inflate(R.layout.post_list,null);
        ImageView postPicture = postView.findViewById(R.id.post_picture);
        TextView postTitle = postView.findViewById(R.id.post_title);
        TextView postDescription = postView.findViewById(R.id.post_description);

        PostModel postModel = postModelList.get(position);
        postPicture.setImageResource(postModel.getPostPicture());
        postTitle.setText(postModel.getPostName());
        postDescription.setText(postModel.getPostDescription());

        return postView;
    }
}
