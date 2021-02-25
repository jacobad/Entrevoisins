package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;



    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }


    private void resetFavoriteToFalse() {
        List<Neighbour> neighbours = service.getNeighbours();
        for(Neighbour neighbour: neighbours){
            neighbour.setFavorite(false);
            service.updateNeighbour(neighbour);
        }
    }


    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void getFavoriteNeighboursWithSuccess() {
        resetFavoriteToFalse();
        Neighbour neighbour = service.getNeighbours().get(0);
        neighbour.setFavorite(true);
        assertEquals(service.getFavoriteNeighbours().size(), 1);
        assertEquals(neighbour.getId(), service.getFavoriteNeighbours().get(0).getId());
        assertTrue(service.getFavoriteNeighbours().get(0).isFavorite());
    }

    @Test
    public void updateFavoriteNeighbourWithSuccess() {

        resetFavoriteToFalse();
        Neighbour neighbour = service.getNeighbours().get(0);
        assertFalse(neighbour.isFavorite());
        neighbour.setFavorite(false);
        Neighbour neighbour1 = new Neighbour(neighbour.getId(),neighbour.getName(),neighbour.getAvatarUrl(),neighbour.getAddress(), neighbour.getPhoneNumber(), neighbour.getAboutMe());
        neighbour1.setFavorite(true);
        service.updateNeighbour(neighbour1);
        assertTrue(neighbour.isFavorite());
    }

    @Test
    public void deleteFavoriteNeighboursWithSuccess() {
        resetFavoriteToFalse();
        Neighbour neighbour = service.getNeighbours().get(0);
        neighbour.setFavorite(true);
        service.updateNeighbour(neighbour);
        neighbour = service.getFavoriteNeighbours().get(0);
        service.deleteNeighbour(neighbour);
        assertFalse(service.getNeighbours().contains(neighbour));
    }
}
