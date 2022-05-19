changes from hw2 to hw3:
- changed getCardAt to throw an error if the given card position was greater than the given row
- added a test to test for shuffled
- added a test to the card constructor that does not allow you to make invalid cards
- changed Card to use enums

changes from hw3 to hw4:
- delegated tasks such as checking for duplicates, building the pyramid to functions so child classes
	can override them, but do not need to access the data of the parent class.
- Made BasicPyramidSoliatire the parent class for Relaxed and TriPeaks, as all the games have the
	same behavior for the majority of things
- Added test cases in TestTextualView and TestController to account for the new types of games.