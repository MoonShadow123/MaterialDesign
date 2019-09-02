package com.example.materialdesign.bean;

import com.example.materialdesign.R;

import java.util.Random;

/**
 * The type Fruit.
 *
 * @author PengLiang
 * Time: 2019/8/27
 */
public class Fruit {
    private String name;
    private int imageId;
    /**
     * The constant fruits.
     */
    public static Fruit[] fruits = {
            new Fruit("香蕉", R.drawable.banana),
            new Fruit("蓝莓", R.drawable.blueberry),
            new Fruit("樱桃", R.drawable.cherry),
            new Fruit("草莓", R.drawable.strawberry),
            new Fruit("芒果", R.drawable.mango)
    };

    /**
     * Gets fruit element.
     *
     * @return the fruit element
     */
    public static Fruit getFruitElement() {
        Random random = new Random();
        int index = random.nextInt(fruits.length);
        return fruits[index];
    }

    /**
     * Instantiates a new Fruit.
     *
     * @param name    the name
     * @param imageId the image id
     */
    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets image id.
     *
     * @return the image id
     */
    public int getImageId() {
        return imageId;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets image id.
     *
     * @param imageId the image id
     */
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

}
