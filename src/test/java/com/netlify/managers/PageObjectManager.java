package com.netlify.managers;

import com.netlify.pages.*;

public class PageObjectManager {
    private BasePage basePage;
    public BasePage getBasePage() {
        return (basePage == null) ? basePage = new BasePage() : basePage;
    }
}
