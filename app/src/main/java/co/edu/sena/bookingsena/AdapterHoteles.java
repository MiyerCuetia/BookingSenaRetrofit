package co.edu.sena.bookingsena;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import co.edu.sena.bookingsena.models.AlojamientoUtilVO;
import co.edu.sena.bookingsena.models.LugarVO;


public class AdapterHoteles extends RecyclerView.Adapter<AdapterHoteles.ViewHolderHoteles> implements View.OnClickListener {
    //Generamos la lista  de tipo Alojamiento
    ArrayList<AlojamientoUtilVO> listAlojamientoUtilVOS;


    //Creamos una propiedad de OnClickListener
    private View.OnClickListener listener;

    //Generamos un constructor que reciba esa lista, ya que el adaptador tiene que ser llamado cada vez que    vamos a pintar un elemento de esa lista de lugares

    public AdapterHoteles(ArrayList<AlojamientoUtilVO> listAlojamientoUtilVOS) {
        this.listAlojamientoUtilVOS = listAlojamientoUtilVOS;
    }

    //El método onCreateViewHolder enlaza el adaptador con el archivo xml item_hotel
    //Inflamos mediante un View el archivo xml item_hotel
    //También le asignamos el evento  OnClickListener, para ponerlo a escuchar al momento de ser seleccionado un item;
    @Override
    public ViewHolderHoteles onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotel, null, false);
        view.setOnClickListener(this);
        return new ViewHolderHoteles(view);
    }
    //Se encarga de hacer la comunicación entre nuestro adaptador y la clase ViewHolderHoteles
    @Override
    public void onBindViewHolder(ViewHolderHoteles holder, int position) {

        holder.tvTitulo.setText(String.valueOf(listAlojamientoUtilVOS.get(position).getLugNombre()));
        holder.tvUbicacion.setText(String.valueOf(listAlojamientoUtilVOS.get(position).getLugDireccion()));
        holder.tvTipoAlojamiento.setText(String.valueOf(listAlojamientoUtilVOS.get(position).getTalNombre()));
        holder.tvPrecio.setText(String.valueOf(listAlojamientoUtilVOS.get(position).getAloPrecio()));
        //holder.imageHotel.setImageResource(listLugares.get(position).getImagenHotel());
    }

    // retorna el tamaño de la lista
    @Override
    public int getItemCount() {
        return listAlojamientoUtilVOS.size();
    }
    // creamos el método setOnClickListener
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;

    }
    // validamos si el listener viene es nulo
    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }
    //Hacemos referencia a los componentes gráficos del archivo xml item_hotel
    public class ViewHolderHoteles extends RecyclerView.ViewHolder {
        TextView tvTitulo;
        TextView tvUbicacion;
        TextView tvTipoAlojamiento;
        TextView tvPrecio;
        ImageView imageHotel;

        public ViewHolderHoteles(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tv_titulo);
            tvUbicacion = itemView.findViewById(R.id.tv_ubicacion);
            tvTipoAlojamiento = itemView.findViewById(R.id.tv_tipoAlojmaiento);
            tvPrecio = itemView.findViewById(R.id.tv_precio);
            imageHotel = itemView.findViewById(R.id.image_hotel);
        }
    }
}
