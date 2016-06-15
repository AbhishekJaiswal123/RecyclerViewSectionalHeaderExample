package com.abhi.model;

/**
 * Created by Abhishek on 10-09-2015.
 */
public class SectionHeaderModel {

        public int firstPosition;
        public int sectionedPosition;
        public  CharSequence title;

        public SectionHeaderModel(int firstPosition, CharSequence title) {
            this.firstPosition = firstPosition;
            this.title = title;
        }

        public CharSequence getTitle() {
            return title;
        }
    }

