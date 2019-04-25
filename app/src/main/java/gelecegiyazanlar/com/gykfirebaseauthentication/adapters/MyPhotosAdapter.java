package gelecegiyazanlar.com.gykfirebaseauthentication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

import gelecegiyazanlar.com.gykfirebaseauthentication.R;
import gelecegiyazanlar.com.gykfirebaseauthentication.models.PhotoModel;

public class MyPhotosAdapter extends BaseAdapter{

    LayoutInflater layoutInflater;
    List<PhotoModel> photoModelList;

    public MyPhotosAdapter(LayoutInflater layoutInflater, List<PhotoModel> photoModel) {
        this.layoutInflater = layoutInflater;
        this.photoModelList = photoModel;
    }


    @Override
    public int getCount() {
        return photoModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return photoModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View photoView = layoutInflater.inflate(R.layout.photo_list,null);
        ImageView postPicture = photoView.findViewById(R.id.user_photo_pl);
        PhotoModel photoModel = photoModelList.get(position);
        postPicture.setImageBitmap(photoModel.getBitmap());
        return photoView;
    }
}
