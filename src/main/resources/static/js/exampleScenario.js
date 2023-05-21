let exampleScenario = `{  
  "title": "Adding a book",
  "actors": [
    "Librarian"
  ],
  "systemActor": "System",
  "steps": [
    {
      "content": "The librarian selects the option to add a new book item",
      "subSteps": []
    },
    {
      "content": "A form is displayed",
      "subSteps": []
    },
    {
      "content": "The librarian provides book details",
      "subSteps": []
    },
    {
      "content": "IF: The librarian wants to add book copies",
      "subSteps": [
        {
          "content": "The librarian selects the option to define copies",
          "subSteps": []
        },
        {
          "content": "The system presents the defined copies",
          "subSteps": []
        },
        {
          "content": "FOR EACH copy:",
          "subSteps": [
            {
              "content": "The librarian selects the option to add a copy",
              "subSteps": []
            },
            {
              "content": "The system prompts for copy details",
              "subSteps": []
            },
            {
              "content": "The librarian provides copy details and confirms",
              "subSteps": []
            },
            {
              "content": "The system informs about successful copy addition and presents the updated list of copies",
              "subSteps": []
            }
          ]
        }
      ]
    },
    {
      "content": "The librarian confirms adding the book",
      "subSteps": []
    },
    {
      "content": "The system informs about successful book addition",
      "subSteps": []
    }
  ]
}`;