@bug
Feature: Test Suite for Defects

  Background:
    Given user is on the application

  Scenario: Product List
    And verify that The product list should contain 6 artworks

  Scenario: Add to Cart
    Then hover over 3 th product
    Then Click Add to Cart 15 times

  Scenario: Pagination Bug 1
    And get default image size
    Then go to "last" page
    And check whether product image size is cropped or not

  Scenario: Filtering bug
    And get default image size
    Then choose "Luxury" for filtering
    Then choose "More than $200" for filtering
    And check whether product image size is cropped or not