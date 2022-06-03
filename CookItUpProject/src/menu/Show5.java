package menu;

import java.util.ArrayList;
import java.util.List;

import recipe.Recipe;

public class Show5 <E> {

    ArrayList<E> lista = new ArrayList<E>();

    public int show5(int i,List<E> recipes,int pagina) {
        boolean seguir = true;
        int cont = 1;
        int z = i;
        while(z < i+5 && seguir == true) {
            if(recipes.size()>z) {
                System.out.println("#" + cont);
                System.out.println(recipes.get(z).toString());
                z++;
                cont ++;
            }
            else { 
                seguir = false;
            }
        }
        return z;

    }
}