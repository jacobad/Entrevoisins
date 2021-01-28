package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }


    // get list des favoris
    @Override
    public List<Neighbour> getFavoriteNeighbours() {


        //cree un tab qui stock mes favoris
        ArrayList favorite = new ArrayList<>();

        //verifie que chaqu'un des mes neighbour sont fav ou pas grace au if
        //puis je l'ajoute a mon tab de favoris
        for (Neighbour neighbour : neighbours) {
            if (neighbour.isFavorite()) {
                favorite.add(neighbour);
            }
        }
        // retourne mon tableau de favoris
        return favorite;
    }



    // fonction qui permet d'ajouer les neighbour en

    @Override
    public void addFavNeighbours(Neighbour neighbour) {

        //Boucle for pour trouver "MON" neighbour dans la list neighbour
        for (Neighbour favNeighbour : getNeighbours()) {
            //Condition pour trouver "MON" neighhbour avec son ID
            if (neighbour.getId() == favNeighbour.getId()) {
                //"Mon" neighbour a été trouver je change son favorite-->(vraie)
                favNeighbour.setFavorite(neighbour.isFavorite());

            }
        }
    }
}
