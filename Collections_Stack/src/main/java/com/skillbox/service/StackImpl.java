package com.skillbox.service;

import com.skillbox.Stack;
import com.skillbox.StackException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class StackImpl implements Stack {
    private final List<Object> list = new LinkedList<>();

    @Override
    public void push(Object element) throws StackException { // add new element to the top of the stack
        list.addLast(element);
    }

    @Override
    public Object pop() throws StackException { // return and remove an element from the top

        return list.remove(list.size() - 1);
    }

    @Override
    public Object peek() { // return the top element but does not remove element

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
        return false ;
    }

    @Override
    public void setMaxSize(int size) {

    }

    @Override
    public void pushAll(Collection src) throws StackException { // add all elements from @src to the stack

        list.add(src.stream().collect(Collectors.toList()));
    }

    @Override
    public void popAll(Collection dst) throws StackException { // pop all elements from stack to @dst
        dst.add(list.stream().collect(Collectors.toList()));
    }
}
