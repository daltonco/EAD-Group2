# Introduction

AdoptMe! Is a nationwide animal shelter that provides their locations with a web service to maintain their animals. They have created a web service called AdoptMe+ to host shelter functions. Each animal is required to have an ID but can be given pictures and notes on the characteristics, which each location can input. Animal data is obtained from SQL data generation. AdoptMe+ will provide functionality using REST endpoints or through a UI.

# Storyboard

<img src="README%20Assets/AdoptMe.png" width=575px>
<img src="README%20Assets/AdoptMeDogs.png" width=575px>
<img src="README%20Assets/AdoptMeSearch.png" width=575px>
<img src="README%20Assets/AdoptMeContact.png" width=575px>

# Functional Requirements

1. As a shelter, we want to be able to catalog our animals, so that we can manage what we have available for adoption.

   **Example 1**

   Given: A feed of animal data is available.

   When: The user selects animal "Cat".

   When: The user adds name "Rico" to a "Cat" specimen.

   Then: The user's Cat will be saved with a name of "Rico".

   **Example 2**

   Given: Animal data is available.

   When: The user searches for "aaaaaaaaaaaaaaaaaaaa".

   Then: AdoptMe+ will not return any results.

   **Example 3**

   Given: Animal data is available, and there is a "Cat".

   When: The user searches for animals with the type "Cat".

   Then: AdoptMe+ will return all animals with the type "Cat".

   **Example 4**

   Given: Animal data are available, and there is a "Dog" with the name "Charles".

   When: The user searches for animals with the name "Charles".

   Then: AdoptMe+ will return at least one "Dog" with the name "Charles".
2. As a shelter, we want to be able to view our animal data by tags, so that we can easily view all animals associated with that tag.

   **Example 1**

   Given: The tag "Family Friendly" is assigned to an animal.

   When: The user selects the "Family Friendly" tag.

   Then: AdoptMe+ will return all animals assigned the "Family Friendly" tag.

   **Example 2**

   Given: Animal data is available, and the tag "Family Friendly" exists.

   When: The user adds assigns the "Family Friendly" tag to some animal.

   Then: The user's animal will be saved with the tag "Family Friendly".
3. As a shelter, we want to be able to upload photos of our animals at any time.

   **Example 1**

   Given: The user is logged in, and has selected a previously saved "Cat" specimen.

   When: The user uploads a valid photo of a "Cat".

   Then: The photo of a "Cat" will be saved to the animal profile.

   **Example 2**

   Given: The user is logged in, and is adding a new "Dog".

   When: The user uploads a valid photo of a "Dog".

   Then: The "Dog" specimen will be created and saved with the uploaded photo.9

# Class Diagram

<img src="README%20Assets/ClassDiagram.png" width=1150px>

# JSON Schema

```json
{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "type": "object",
  "properties": {
    "dogId": {
      "type": "integer"
    },
    "fullName": {
      "type": "string"
    },
    "breed": {
      "type": "string"
    },
    "age": {
      "type": "integer"
    }
  },
  "required": [
    "dogId",
    "fullName",
    "breed",
    "age"
  ],
  "additionalProperties": false,
  "methods": {
    "setName": {
      "type": "function"
    },
    "getName": {
      "type": "function",
      "parameters": [
        {
          "name": "dogId",
          "type": "integer"
        }
      ],
      "returns": "string"
    },
    "setBreed": {
      "type": "function"
    },
    "getBreed": {
      "type": "function",
      "parameters": [
        {
          "name": "dogId",
          "type": "integer"
        }
      ],
      "returns": "string"
    }
  }
}
```

# Scrum Roles

Product Owner/Scrum Master: Colton Dalton

UI Specialist: Zach Thomas

Business Logic: Zach McIntosh

Tester: Brendan Payne

# Github Project

[https://github.com/daltonco/EAD-Group2](https://github.com/daltonco/EAD-Group2)

# Milestones

[Milestone 1](https://github.com/daltonco/EAD-Group2/milestones)

# Meeting Time/Link

[Thursdays @ 5:30
pm](https://teams.microsoft.com/l/meetup-join/19%3ameeting_ZWU0NDBiMzktZGRmYy00OTA0LTkyYzMtMmZmZmI0NTE0MTEy%40thread.v2/0?context=%7b%22Tid%22%3a%22f5222e6c-5fc6-48eb-8f03-73db18203b63%22%2c%22Oid%22%3a%22195598bf-cf9f-4bad-ba3c-e470de641d98%22%7d)
