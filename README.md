# SephoraItemsDisplay
This application display a list of items from [here](https://sephoraandroid.github.io/testProject/items.json) associate with reviews from [here](https://sephoraandroid.github.io/testProject/reviews.json).

The first page allow to search items from the name, then you can click on an item to discover all of his reviews.

## This project use:

- Hilt for dependency injection
- Retrofit and kotlin serialization to get items and reviews from api
- Room for local storage and display on offline mode
- Coroutine and flow for asynchronous data access

## To improve
- Create modules for every package present in domains, features, libraries and repositories.  
- Add tests for mappers and viewModel
- Use viewBinding instead of findViewById
- A prettier UI (maybe with compose)