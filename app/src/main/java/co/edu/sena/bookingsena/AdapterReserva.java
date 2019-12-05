package co.edu.sena.bookingsena;

import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.edu.sena.bookingsena.models.AlojamientoUtilVO;
import co.edu.sena.bookingsena.models.ReservaUsuarioUtilVO;


public class AdapterReserva extends RecyclerView.Adapter<AdapterReserva.ViewHolderReserva>{
    //Generamos la lista  de tipo Alojamiento
    ArrayList<ReservaUsuarioUtilVO> listReservaUsuarioUtilVO;


    //Creamos una propiedad de OnClickListener
    private View.OnClickListener listener;

    //Generamos un constructor que reciba esa lista, ya que el adaptador tiene que ser llamado cada vez que    vamos a pintar un elemento de esa lista de lugares

    public AdapterReserva(ArrayList<ReservaUsuarioUtilVO> listReservaUsuarioUtilVO) {
        this.listReservaUsuarioUtilVO = listReservaUsuarioUtilVO;
    }

    //El método onCreateViewHolder enlaza el adaptador con el archivo xml item_hotel
    //Inflamos mediante un View el archivo xml item_hotel
    //También le asignamos el evento  OnClickListener, para ponerlo a escuchar al momento de ser seleccionado un item;
    @Override
    public ViewHolderReserva onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reserva, null, false);
        return new ViewHolderReserva(view);
    }
    //Se encarga de hacer la comunicación entre nuestro adaptador y la clase ViewHolderHoteles
    @Override
    public void onBindViewHolder(ViewHolderReserva holder, int position) {

        holder.tvTitulo.setText(String.valueOf(listReservaUsuarioUtilVO.get(position).getLugNombre()));
        holder.tvUbicacion.setText(String.valueOf(listReservaUsuarioUtilVO.get(position).getLugDireccion()));
        holder.tvTipoAlojamiento.setText(String.valueOf(listReservaUsuarioUtilVO.get(position).getTalNombre()));
        holder.tvPrecio.setText(String.valueOf(listReservaUsuarioUtilVO.get(position).getAloPrecio()));

        holder.tvFechaReserva.setText(String.valueOf(listReservaUsuarioUtilVO.get(position).getResFechaRegistro()));
        holder.tvCod.setText(String.valueOf(listReservaUsuarioUtilVO.get(position).getAloCodigo()));

        holder.tvResFechaLlegada.setText(String.valueOf(listReservaUsuarioUtilVO.get(position).getResFechaLlegada()));
        holder.tvResFechaSalida.setText(String.valueOf(listReservaUsuarioUtilVO.get(position).getResFechaSalida()));
        holder.tvAloPrecio.setText(String.valueOf(listReservaUsuarioUtilVO.get(position).getAloPrecio()));

        holder.tvDesAlojamiento.setText(String.valueOf(listReservaUsuarioUtilVO.get(position).getLugDescripcion()));

        boolean isExpanded = listReservaUsuarioUtilVO.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        //holder.imageHotel.setImageResource(listLugares.get(position).getImagenHotel());
    }

    // retorna el tamaño de la lista
    @Override
    public int getItemCount() {
        return listReservaUsuarioUtilVO.size();
    }

    //Hacemos referencia a los componentes gráficos del archivo xml item_hotel
    public class ViewHolderReserva extends RecyclerView.ViewHolder {
        LinearLayout llContentTitle;
        TextView tvTitulo;
        TextView tvUbicacion;
        TextView tvTipoAlojamiento;
        TextView tvPrecio;
        ImageView imageHotel;

        ConstraintLayout expandableLayout;

        TextView tvFechaReserva;
        TextView tvCod;
        TextView tvResFechaLlegada;
        TextView tvResFechaSalida;
        TextView tvAloPrecio;
        TextView tvDesAlojamiento;



        public ViewHolderReserva(@NonNull View itemView) {
            super(itemView);
            llContentTitle = itemView.findViewById(R.id.ll_contentTitle);
            tvTitulo = itemView.findViewById(R.id.tv_titulo);
            tvUbicacion = itemView.findViewById(R.id.tv_ubicacion);
            tvTipoAlojamiento = itemView.findViewById(R.id.tv_tipoAlojmaiento);
            tvPrecio = itemView.findViewById(R.id.tv_precio);
            imageHotel = itemView.findViewById(R.id.image_hotel);

            expandableLayout = itemView.findViewById(R.id.expandableLayout);

            tvFechaReserva = itemView.findViewById(R.id.tv_fechaReserva);
            tvCod = itemView.findViewById(R.id.tv_codigoAlojamiento);

            tvResFechaLlegada = itemView.findViewById(R.id.tv_resFechaLlegada);
            tvResFechaSalida = itemView.findViewById(R.id.tv_resFechaSalida);
            tvAloPrecio = itemView.findViewById(R.id.tv_aloPrecio);

            tvDesAlojamiento = itemView.findViewById(R.id.tv_desAlojamiento);

            llContentTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ReservaUsuarioUtilVO reservaUsuarioUtilVO = listReservaUsuarioUtilVO.get(getAdapterPosition());
                    reservaUsuarioUtilVO.setExpanded(!reservaUsuarioUtilVO.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
