package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

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


    // get list neighbour's fav
    @Override
    public List<Neighbour> getFavoriteNeighbours() {


        //create an array that stock my favorites
        ArrayList favorite = new ArrayList<>();

        //check that each of my neighbors are fav or not 'thanks to' the IF condition
        //then add it to my fav's array
        for (Neighbour neighbour : neighbours) {
            if (neighbour.isFavorite()) {
                favorite.add(neighbour);
            }
        }
        return favorite;
    }



    @Override
    public void updateNeighbour(Neighbour updatedNeighbour) {

        //For loop to find "MY" neighbor in the neighbor list
        for (Neighbour neighbour : neighbours) {
            //Condition to find "MY" neighbor with his ID
            if (updatedNeighbour.getId() == neighbour.getId()) {
                //"My" neighbor has found I change his favorite -> (true)
                neighbour.setFavorite(updatedNeighbour.isFavorite());
                return;

            }
        }
    }
}
