# Blackjack Application

### Description

This is a console-based Blackjack game.  It follows standard rules for Blackjack, including soft Aces.  It doesn't allow for splitting on pairs or multiple players, but the UI is good.  It covers the first card for the dealer until the player is finished hitting and finally stays.  Then the card is flipped and the dealer hits until exceeding 17 or bust. The player has the option to play again.

There is a known bug when the player busts. The screen prints the same message three times before giving the player the choice to play again or not. Despite this, the game runs effectively and the bug was beyond the scope of time left available for bug hunting.


Topics covered in Week 4:

- Regular Expressions (REGEX)
- Maps and the Map interface
- Collection sorting
- HashMap internals (and adding hash and equals methods to classes)
- Java Virtual Machine (JVM) & Java Runtime Environment (JRE)
- The args[] array
- Enumerated types

### Technologies Used

- Java
- Eclipse
- Git
- GitHub
- Markdown (for README.md)
- Slack
- Zoom
- Browser (Chrome-focused)
- Unix/Linux Terminal

### Lessons Learned

I spent about 30% of my time planning the game (outline on paper and constructing classes). I spent about 20% of my time building an effective MVP for the game.  The last 50% was debugging and refactoring.

This is classic 80/20 Pareto for me.  Getting to an MVP went fast, but making the code run smoothly and as expected for a non-technical user was challenging.  Many of the issues I dealt with were not compile-time exceptions that the IDE would have caught.  Tracing them usually led to a line being out of place, outside of the curly braces scope it needed to be in. I spent a lot of time stepping through the debugger to test various outcomes based on the random cards dealt in each hand.

My ability to write code is becoming more fluent.  My ability to write beautiful code is still a long ways away.  

There's a philosophy in education and learning theory about the contrast between Tacit and Explicit knowledge.  Where Explicit Knowledge can be documented and shared easily (in a book, textbook, class/course, video, or other reference) -- Tacit Knowledge is knowledge that can't be easily explained or documented.  It can only be learned through either trial-and-error or mentorship.  The classic examples I use to describe this are the Japanese chicken sexers (made famous in the Malcolm Gladwell book "Blink") or the Japanese sword master.  If you want to be a great Japanese sword master, you must sit at the feet of the master and watch, learn to pump the bellows to the right heat (watch), learn to maintain the smithery (watch), and only after seeing the master's technique can the student finally begin to put the hammer to molten steel.

I suspect that my ability to write beautiful code will only come with sitting at the feet of the master.  I had this experience on Friday night as I started this project, gaining some great perspective from the Skill Distillery instructors that deeply shaped my views on how to create object-oriented design.

The wisdom of good code is finally becoming visible to me, and I want to sponge it up as much as possible. I can see what I don't know, and I want to know it.