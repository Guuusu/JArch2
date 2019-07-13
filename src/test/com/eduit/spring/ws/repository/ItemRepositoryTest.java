package com.eduit.spring.ws.repository;

import com.eduit.spring.ws.model.Item;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository;
    private List<Item> items;
    @Test
    public void shouldFindByName(){
        givenItemRepositoryWithThreeitems();
        whenFindByName();
        thenValidate();
    }

    private void whenFindByName() {
        items =  itemRepository.getByName("Item");
    }
    private void thenValidate() {
        Item item = items.get(0);
        Item item2 = items.get(1);

        // Cointrol por tamaño de la lista
        Assert.assertEquals(2,items.size());

        // Control por nombre para saber si el filtro lo hizo bien
        items.forEach(i -> Assert.assertEquals("Item",i.getName()));

        //Control de Valores numericos
        Assert.assertEquals(10L,item.getQuantity().longValue());
        Assert.assertEquals(10.10F,item.getPrice().floatValue(),0);
        Assert.assertEquals(100L,item2.getQuantity().longValue());
        Assert.assertEquals(20.10F,item2.getPrice().floatValue(),0);
    }

    private void givenItemRepositoryWithThreeitems() {
        itemRepository.save(new Item("Item",10.10F,10));
        itemRepository.save(new Item("Item2",10.10F,10));
        itemRepository.save(new Item("Item",20.10F,100));
    }

}
