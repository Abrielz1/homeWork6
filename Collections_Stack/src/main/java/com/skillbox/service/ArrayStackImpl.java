package com.skillbox.service;

import com.skillbox.StackException;

import java.util.Collection;

public class ArrayStackImpl<E> implements Stack<E> {

    private E[] elements;

    private int MAX_SIZE;

    private int size;

    public ArrayStackImpl() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public ArrayStackImpl(int initialCapacity) {
        this.elements = (E[]) new Object[initialCapacity];
        this.MAX_SIZE = initialCapacity;
        this.size = 0;
    }

    @Override
    public void push(E element) throws StackException { // add new element to the top of the stack

        if (this.isFull()) {
            throw new StackException("Stack is full");
        }

        elements[size++] = element;
    }

    @Override
    public E pop() throws StackException {  // return and remove an element from the top

        if (this.isEmpty()) {
            throw new StackException("Stack is empty");
        }

        E element = elements[--size];
        elements[size] = null;
        return element;
    }

    @Override
    public E peek() throws StackException { // return the top element but doesn’t remove

        if (this.isEmpty()) {
            return null;
        }

        return elements[--size];
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.getSize() == 0;
    }

    @Override
    public boolean isFull() {
        return this.getSize() == this.MAX_SIZE;
    }

    @Override
    public void setMaxSize(int size) {
        this.MAX_SIZE = size;
    }

    @Override
    public void pushAll(Collection<? extends E> src) throws StackException { // add all elements from @src to the stack

        if (src.size() >= MAX_SIZE || this.getSize() + src.size() > MAX_SIZE) {
            throw new StackException("Not enough space to push all elements");
        }

        for (E e : src) {
            this.push(e);
        }
    }

    @Override
    public void popAll(Collection<? super E> dst) throws StackException { // pop all elements from stack to @dst
        while (!isEmpty()) {
            dst.add(this.pop());
        }
    }
}
