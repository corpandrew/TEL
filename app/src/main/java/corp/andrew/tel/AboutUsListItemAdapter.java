package corp.andrew.tel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by corpa on 10/7/16.
 */

public class AboutUsListItemAdapter extends ArrayAdapter<Developer> {

    private LayoutInflater inflater;

    public AboutUsListItemAdapter(Context context, int resource) {
        super(context, resource);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView,
                        ViewGroup parent) {

        final ViewHolder holder;
        final Developer developer = getItem(position);

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = inflater.inflate(R.layout.list_item, null);

            holder.picture = (ImageView) convertView.findViewById(R.id.developerPictureImageView);

            holder.name = (TextView) convertView.findViewById(R.id.developerNameTextView);

            holder.title = (TextView) convertView.findViewById(R.id.developerTitleTextView);

            holder.schoolName = (TextView) convertView.findViewById(R.id.developerSchoolNameTextView);

            holder.bio = (TextView) convertView.findViewById(R.id.developerBioTextView);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.position = position;

        assert developer != null;

        holder.name.setText(developer.getPersonName());

        holder.title.setText(developer.getTitle());

        holder.schoolName.setText(developer.getSchoolName());

        holder.bio.setText(developer.getBio());


        return convertView;
    }

    private static class ViewHolder {
        private ImageView picture;
        private TextView name, title, schoolName, bio;
        private int position;
    }


}
