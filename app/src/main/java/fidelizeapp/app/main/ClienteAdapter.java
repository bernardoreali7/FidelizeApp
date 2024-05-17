package fidelizeapp.app.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class ClienteAdapter extends ArrayAdapter<Cliente> {
    public ClienteAdapter(Context context, List<Cliente> clientes) {
        super(context, 0, clientes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cliente contact = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.clientes_list_item, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewPhone = convertView.findViewById(R.id.textViewPhone);

        textViewName.setText(contact.getName());
        textViewPhone.setText(contact.getPhone());

        return convertView;
    }
}
