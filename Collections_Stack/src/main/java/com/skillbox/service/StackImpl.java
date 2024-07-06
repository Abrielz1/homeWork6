package com.skillbox.service;

import com.skillbox.Stack;
import com.skillbox.StackException;
import java.util.Collection;
import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

public class StackImpl implements Stack {

    private int MAX = 12;

    private Deque<Object> list = new LinkedBlockingDeque<>(MAX);

    @Override
    public void push(Object element) throws StackException { // add new element to the top of the stack
        if (this.getSize() >= MAX) {
            throw new StackException("StackException");
        }
        list.addLast(element);
    }

    @Override
    public Object pop() throws StackException { // return and remove an element from the top

        System.out.printf(String.format("size: %d", list.size()));
        if (list.isEmpty() || this.getSize() == 0) {
            throw new StackException("");
        }

        System.out.printf("size %d ", list.size());
        return list.removeLast();
    }

    @Override
    public Object peek() throws StackException { // return the top element but does not remove element
        if (list.isEmpty() || this.getSize() == 0) {
            throw new StackException("");
        }

        return list.getLast();
    }

    @Override
    public int getSize() {

        return list.size();
    }

    @Override
    public boolean isEmpty() {

        return this.getSize() == 0;
    }

    @Override
    public boolean isFull() {

        return this.getSize() >= MAX ;
    }

    @Override
    public void setMaxSize(int size) {

        MAX = size;

        list = new LinkedBlockingDeque<>(MAX);
    }

    @Override
    public void pushAll(Collection src) throws StackException { // add all elements from @src to the stack

        for (Object i : src) {
            this.push(i);
        }

    }

    @Override
    public void popAll(Collection dst) throws StackException { // pop all elements from stack to @dst
        while (!this.isEmpty()) {
            dst.add(this.pop());
        }
    }
}
