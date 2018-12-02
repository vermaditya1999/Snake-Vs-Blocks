package com.snakevsblocks.util;

import java.io.Serializable;

/**
 * This class is used for creating and manipulating a 2-dimensional mathematical Vector.
 */
public class Vector implements Serializable {

    /**
     * X component of the Vector.
     */
    public double x;

    /**
     * Y component of the Vector.
     */
    public double y;

    /**
     * Returns Create a random unit vector.
     *
     * @return a random unit Vector.
     */
    public static Vector random() {
        // Random x and y in range (-1, 1)
        double x = Random.nextDouble() * 2 - 1;
        double y = Random.nextDouble() * 2 - 1;

        Vector v = new Vector(x, y);
        v.normalize();

        return v;
    }

    /**
     * Add two vectors and return the resultant Vector.
     * @param first first vector.
     * @param second second vector.
     * @return the resultant vector.
     */
    public static Vector add(Vector first, Vector second) {
        return new Vector(first.x + second.x, first.y + second.y);
    }

    /**
     * Subtract second Vector from first Vector and return the resultant Vector
     * @param first first vector.
     * @param second second vector.
     * @return resultant vector.
     */
    public static Vector sub(Vector first, Vector second) {
        return new Vector(first.x - second.x, first.y - second.y);
    }

    /**
     * Returns the distance between two Vectors.
     * @param first first vector.
     * @param second second vector.
     * @return distance between the two vectors.
     */
    public static double dist(Vector first, Vector second) {
        return Vector.sub(first, second).mag();
    }

    /**
     * Creates a new Vector.
     * @param x x coordinate of the vector.
     * @param y y coordinate of the vector.
     */
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Add a vector.
     * @param v the vector to be added.
     */
    public void add(Vector v) {
        x += v.x;
        y += v.y;
    }

    /**
     * Subtracted a vector.
     * @param v the vector to be subtracted.
     */
    public void sub(Vector v) {
        x -= v.x;
        y -= v.y;
    }

    /**
     * Scale the vector.
     * @param m the scaling factor.
     */
    public void mult(double m) {
        x *= m;
        y *= m;
    }

    /**
     * Linearly interpolate between two vectors.
     * @param dest the destination vector.
     * @param val interpolation vector.
     */
    public void lerp(Vector dest, double val) {
        this.x += (dest.x - this.x) * val;
        this.y += (dest.y - this.y) * val;
    }

    /**
     * Get magnitude of the vector.
     * @return the magnitude of the vector.
     */
    public double mag() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Normalize the vector.
     */
    public void normalize() {
        double mag = this.mag();

        if (mag != 0) {
            x /= mag;
            y /= mag;
        }
    }

    /**
     * Change the magnitude of the vector.
     * @param m The resultant magnitude.
     */
    public void setMag(double m) {
        this.normalize();
        this.mult(m);
    }

    /**
     * Get a copy of the vector.
     * @return The copy of the vector.
     */
    public Vector copy() {
        return new Vector(x, y);
    }

    /**
     * Change the vector.
     * @param v The resultant vector.
     */
    public void set(Vector v) {
        x = v.x;
        y = v.y;
    }
}
