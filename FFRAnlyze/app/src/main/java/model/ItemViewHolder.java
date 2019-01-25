package model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.qualidade.lg.ffr_anlyze.ListaCidadesActivity;
import com.qualidade.lg.ffr_anlyze.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manoel on 17/02/2018.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {
    ArrayList<ItemTeste> lista;
    final TextView itemnome;
    TextView anoAnterior;
    TextView anoAtual;
    TextView anoAnteriorS;
    TextView anoAtualS;
    TextView valorAnterior;
    TextView valorAtual;
    TextView valorAnteriorS;
    TextView valorAtualS;
    TextView posicaoRanking;
    TextView valorImproved;
    TextView valorImprovedS;
    ImageButton imageIcon;
    ImageButton imageIconS;
    Data dataApp;
    String nomeProduto;
    String nomeEstado;
    Button iconeCor;
    Button iconeCorS;
    Context contexto;
    public ItemViewHolder(Context context, final View itemView, final ArrayList<ItemTeste> lista, Data data, String produto, String estado) {
        super(itemView);
        this.lista = lista;
        contexto = context;
        dataApp = data;
        nomeProduto = produto;
        nomeEstado = estado;
        itemnome = (TextView) itemView.findViewById(R.id.itemNome);
        anoAnterior = itemView.findViewById(R.id.anoAnterior);
        anoAtual = itemView.findViewById(R.id.anoAtual);
        valorAnterior = itemView.findViewById(R.id.valorAnoAterior);
        valorAtual = itemView.findViewById(R.id.valorAnoAtual);
        valorImproved = itemView.findViewById(R.id.valorImproved);
        imageIcon = itemView.findViewById(R.id.imgIcon);
        iconeCor = itemView.findViewById(R.id.iconeCor);
        valorAnteriorS = itemView.findViewById(R.id.valorAnoAteriorS);
        valorAtualS = itemView.findViewById(R.id.valorAnoAtualS);
        valorImprovedS = itemView.findViewById(R.id.valorImprovedS);
        imageIconS = itemView.findViewById(R.id.imgIconS);
        iconeCorS = itemView.findViewById(R.id.iconeCorS);
        posicaoRanking = itemView.findViewById(R.id.posicaoRanking);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pos = getAdapterPosition();
                Intent intent = new Intent(itemView.getContext(),ListaCidadesActivity.class);
                intent.putExtra("dataApp", dataApp);
                intent.putExtra("produto", nomeProduto);
                intent.putExtra("estado", nomeEstado);
                intent.putExtra("nome",  lista.get(pos).getNome());
                contexto.startActivity(intent);
            }
        });
    }
}
