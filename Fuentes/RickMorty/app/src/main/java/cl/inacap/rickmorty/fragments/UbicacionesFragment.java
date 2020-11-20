package cl.inacap.rickmorty.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cl.inacap.rickmorty.R;
import cl.inacap.rickmorty.adapters.UbicacionesAdapter;
import cl.inacap.rickmorty.dto.Ubicacion;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UbicacionesFragment} factory method to
 * create an instance of this fragment.
 */
public class UbicacionesFragment extends Fragment {

    private List<Ubicacion> ubicaciones = new ArrayList<>();
    private ListView ubicacionesList;
    private UbicacionesAdapter ubicacionesAdapter;
    private RequestQueue queue;
    private String e = "error de peticion";


    public UbicacionesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        queue = Volley.newRequestQueue(this.getContext());
        this.ubicacionesList = getView().findViewById(R.id.ubicaciones_list_view);
        this.ubicacionesAdapter = new UbicacionesAdapter(this.getActivity()
                ,R.layout.list_ubicaciones,this.ubicaciones);
        this.ubicacionesList.setAdapter(this.ubicacionesAdapter);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, "https://rickandmortyapi.com/api/location"
                , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ubicaciones.clear();
                    Ubicacion[] arreglo = new Gson()
                            .fromJson(response.getString("results")
                            ,Ubicacion[].class);
                    ubicaciones.addAll(Arrays.asList(arreglo));
                }catch (Exception ex){
                    ubicaciones.clear();
                    Log.e("UBICACIONES_FRAGMENT",e);
                }finally {
                    ubicacionesAdapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ubicaciones.clear();
                Log.e("UBICACIONES_FRAGMENT","Error de respuesta");
                ubicacionesAdapter.notifyDataSetChanged();
            }
        });
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ubicaciones, container, false);
    }
}