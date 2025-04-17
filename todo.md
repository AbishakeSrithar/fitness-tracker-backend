# Tasks
## Streaming
- [x] Figure out if LoFi girl music gets copyrighted/issues
- [x] Check if the Ultrawide streaming is okay
- [x] Check if font size is okay
  - [x] Intellij is good, Vscode needs ++ (maybe leave and check with new video settings first (that worked))
- [ ] Check if Audio of me + music is okay
  - [ ] Too quiet, have upped the gain on Mic
  - [ ] Max gain!! 
- [x] Video makes text weird, downscaling issue? works on 4k 16:9 monitor so not downscaling issue, just ultrawide?
  - [x] Switched widescreen to lower res + grid to 4x4 

## Fitness Tracker App
- [x] Add a theme to my intellij
- [x] Verify everything works
- [x] Document how to get it to work
- [x] Check if when we change the SQL file, everything dies, if so why, can we reset the DB instead of incrementing .sql files during local development
- [x] Check if tests work (they probably don't)
- [x] Get context load tests dockerised and working
  - [x] Figure out why it not works, what are those annotations doing??
- [x] Rename/Refactor functions and filenames
- [x] Add more tests
  - [x] Service Tests
  - [x] Controller Tests
    - [x] Entries remaining
  - [ ] NO AI
  - [ ] Data for more exercises as part of init (not for tests, for normal)
  - [ ] Plan what's next, what other endpoints do we need and how to do them
  - [ ] Add CRUD endpoints+repocalls (check ReadMe for full list)

## Questions for later
- [ ] Figure out why intellij theme keeps resetting
- [ ] The contextLoads test requires the docker db to be up (why aren't my test containers up like for the other tests)
- [ ] Repository/Models test? Necessary? Use README Baeldung link
- [ ] Semi colons at end? Linter for Kotlin?
- [ ] Auto clean up imports?

## What we've learnt
- [ ] @WebMvcTest is a lightweight only get the controller beans, whereas @SpringBootTest launches all the beans? @WebMvcTest(WorkoutController::class) will work just for specified Controller without needing all controllers mocked (the case with no args): https://docs.spring.io/spring-boot/reference/testing/spring-boot-applications.html#testing.spring-boot-applications