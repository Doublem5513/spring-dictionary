# spring-dictionary
Simple WebApp dictionary, using spring boot, spring-data-jpa and hibernate (H2 db).

## Steps to running this application
1. Configure database settings in: **config.MainConfiguration#dataSource**
2. Start application by running: **Application** class
3. Initialize database by calling: **GET /dictionary/import method**
  - **WARNING!** This will take a long time (2-3 hours). During this time databse will be initialized!
4. Search database by calling method: **GET /dictionary/import?eng=house**
  This should return list of croatina words for "house"
  
## Aditional query params
- Use parameter "like" to specify if you want search to be exact match of the english word, or "like" search, meaning that english word can appear anywhere.
  If this parameter is not used, **false** is default value.
  ### Example:
  - if you use "GET /dictionary/import?eng=house?**like=false"** it will return only exact match of the "house":
    * house
  - if you use "GET /dictionary/import?eng=house?**like=true"** it will return all words where "house" appears:
    * house
    * housewife
    * houses
    * workhouse
  
  **WARNING!** Like search is **much slower** than exact search, because database can't use indexes for searching.
