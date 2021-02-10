package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
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

        Neighbour neighbour = service.getNeighbours().get(0);
        neighbour.setFavorite(true);
        assertEquals(service.getFavoriteNeighbours().size(), 1);
        assertEquals(neighbour.getId(), service.getFavoriteNeighbours().get(0).getId());
        assertTrue(service.getFavoriteNeighbours().get(0).isFavorite());
    }

    @Test
    public void updateFavoriteNeighbourWithSuccess() {

        Neighbour neighbour = service.getNeighbours().get(0);
        neighbour.setFavorite(false);
        Neighbour neighbour1 = new Neighbour(neighbour.getId(),neighbour.getName(),neighbour.getAvatarUrl(),neighbour.getAddress(), neighbour.getPhoneNumber(), neighbour.getAboutMe());
        neighbour1.setFavorite(true);
        service.updateFavNeighbours(neighbour1);
        assertTrue(neighbour.isFavorite());
    }


    @Test
    public void deleteFavoriteNeighboursWithSuccess() {
        Neighbour neighbourToDelete = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(0);
        neighbourToDelete.setFavorite(true);
        service.getFavoriteNeighbours();
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getFavoriteNeighbours().contains(neighbourToDelete));
    }
}
