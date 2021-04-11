@EndToEnd
Feature: End to End test for Social Platform API
  Description: The purpose of this test is to cover end to end happy path for social media platform website

  Book Store Swagger URL : https://jsonplaceholder.typicode.com/

  #This step is optional as there is no authorization endpoint provided
  Background: User generates token for Authorization
    Given I am an authorized user

  Scenario: User is able view Posts by post Id and validate the contents
    Given I open post with mentioned postId
      | 1 |
    Then I validate the contents of that post
      | userId | id | title                                                          | body                                                                                                                                                                   |
      | 1      | 1  | "to provide or to reject the blind are welcome option to find" | "And it takes \ nsuscipit follow accepted lightly with \ nreprehenderit discomfort may be the entire \ nnostrum of the things that happens is that they are extremely" |

  Scenario: User is able fetch post list once and from that list view any post (dont make another call to particular post)
    Given A list of posts that are available
    When I open given post from list
      | 1 |
    Then I validate the contents of that post
      | userId | id | title                                                          | body                                                                                                                                                                   |
      | 1      | 1  | "to provide or to reject the blind are welcome option to find" | "And it takes \ nsuscipit follow accepted lightly with \ nreprehenderit discomfort may be the entire \ nnostrum of the things that happens is that they are extremely" |

  Scenario: User is able view Comments by comment Id and validate the contents
    Given I open comment with mentioned commentId
      | 1 |
    Then I validate the contents of that comment
      | postId | id | name                      | email              | body                                                                                                                                    |
      | 1      | 1  | to work out how they work | Eliseo@gardner.biz | cheering as if it is indeed a great pleasure to the \ ntempora which needs \ fluid and the like \ nreiciendis for wisdom and denouncing |

  Scenario: User is able view Comments from the existing comments list ( DO NOT make get call to particular comments again)
    Given A list of comments that are available
    When I open given comment from list
      | 1 |
    Then I validate the contents of that comment
      | postId | id | name                      | email              | body                                                                                                                                    |
      | 1      | 1  | to work out how they work | Eliseo@gardner.biz | cheering as if it is indeed a great pleasure to the \ ntempora which needs \ fluid and the like \ nreiciendis for wisdom and denouncing |


  Scenario: User is able view Users
    Given A list of users that are available
    When I open user with mentioed userId
      | 1 |

  Scenario: User is able to do a post
    Given I do new post
      | userId | id  | title                                                          | body                                                                                                                                                                   |
      | 101    | 101 | "to provide or to reject the blind are welcome option to find" | "And it takes \ nsuscipit follow accepted lightly with \ nreprehenderit discomfort may be the entire \ nnostrum of the things that happens is that they are extremely" |
    Then I validate the contents of that post
      | userId | id  | title                                                          | body                                                                                                                                                                   |
      | 101    | 101 | "to provide or to reject the blind are welcome option to find" | "And it takes \ nsuscipit follow accepted lightly with \ nreprehenderit discomfort may be the entire \ nnostrum of the things that happens is that they are extremely" |


  Scenario: User is able to open any post and post comment on that post
    Given I open post with mentioned postId
      | 1 |
    Then I post comment on above post
      | postId | id | name                      | email              | body                                                                                                                                    |
      | 1      | 1  | to work out how they work | Eliseo@gardner.biz | cheering as if it is indeed a great pleasure to the \ ntempora which needs \ fluid and the like \ nreiciendis for wisdom and denouncing |
    Then I validate the contents of that comment
      | postId | id | name                      | email              | body                                                                                                                                    |
      | 1      | 1  | to work out how they work | Eliseo@gardner.biz | cheering as if it is indeed a great pleasure to the \ ntempora which needs \ fluid and the like \ nreiciendis for wisdom and denouncing |

  Scenario: User is able to open any post made by him
    Given A list of users that are available
    Then I open all post from user and validate total posts
      | userId | totalPosts |
      | 1      | 10         |

  Scenario: User is able to open any comments made by him
    Given A list of users that are available
    Then I open all comments from user for given post and validate total comments
      | postId | totalComments |
      | 1      | 5            |