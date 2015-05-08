/**
 * The ordered Step set used to order successive states.
 */
open util/ordering[Step]
sig Step{}

/**
 *	Field and Date atoms used for scheduling games.
 */
some sig Field{}
some sig Date{}

/**
 * Scheduled games take place on a field on a given date.
 */
some sig Game {
	where : Field -> Step,
	when	 : Date -> Step
}

/**
 * There is one field complex with a schedule of games.
 */
one sig FieldComplex {
	schedule : Game -> Step
}

/**
 * Games *not* on the schedule have no associated field
 * or date.
 */
pred NotOnSchedule_NoFieldNoDate[st : Step] {
	all g : Game - FieldComplex.schedule.st | no g.where.st && no g.when.st
}

/**
 * Games that *are* on the schedule have one field and one
 * date.
 */
pred OnSchedule_HaveFieldAndDate[st : Step] {
	all g : FieldComplex.schedule.st | one g.where.st && one g.when.st
}

/**
 * Games on the same field are scheduled for
 * different dates.
 */
pred SameFieldMeansDifferentDates[st : Step] {
	all disj g1, g2 : FieldComplex.schedule.st {
		g1.where.st = g2.where.st => g1.when.st != g2.when.st
	}
}

/**
 * The invariant - the conjunction of all the
 * state predicates.
 */
pred Invariant[st : Step] {
	NotOnSchedule_NoFieldNoDate[st]
	OnSchedule_HaveFieldAndDate[st]
	SameFieldMeansDifferentDates[st]
}

/**
 * Find a series of states such that the when relation for the step
 * and the were relation for the step are different from those for
 * all other steps.
 */
run {
	all st : Step {
		Invariant[st]
		no st' : Step - st | when.st = when.st' || where.st = where.st'
	}
} for 4

/************* Initial State *************/

/**
 * In the initial state there are no games scheduled.
 * There are consequences for games that must also be
 * explicitly addressed.
 */
pred init[st : Step] {
	no schedule.st
	no where.st
	no when.st
}

assert init_closed {
	all st : Step | init[st] => Invariant[st]
}
check init_closed for 6 but 1 Step

pred init_exists {
	some st : Step | init[st]
}
run init_exists for 4 but 1 Step

/************* Schedule a Game *************/

/**
 * Schedule a new game 'g' in field 'f' at date 'd' if this
 * will not cause a conflict:
 *		- The new game is not currently scheduled 
 *		- No other games are scheduled on the requested field and date.
 *		- The operation completes with the new game scheduled and
 *		  being specifically assigned the field and date requested	
 *		- Nothing else about the schedule or the other games is affected.
 */
pred scheduleGame[g : Game, f : Field, d : Date, st : Step] {
	/*
	 * Preconditions
	 */
	// Fill This In

	/**uncomment* let st' = next[st] { **/
		/*
		 * Effects
		 */
		// Fill This In

		/*
		 * Frame
		 */
		// Fill This In
	/**uncomment* } */
}

assert scheduleGame_closed {
	all st : Step, g : Game, f : Field, d : Date {
		Invariant[st] && scheduleGame[g, f, d, st] =>
			Invariant[next[st]]
	}
}
check scheduleGame_closed for 6 but 2 Step

pred scheduleGame_exists {
	some st : Step - last, g : Game, r : Field, t : Date {
		Invariant[st]
		scheduleGame[g, r, t, st]
	}
}
run scheduleGame_exists for 4 but 2 Step

/************* Cancel a Game *************/

/**
 * Cancel a scheduled game 'g' by removing it from the schedule.
 * This may require updating information associated with 'g'.
 *		- The game is currently scheduled 
 *		- The operation completes with the game removed from the 
 *		  schedule and releasing the assigned field and date.	
 *		- Nothing else about the schedule or the other games is affected.
 */
pred cancelGame[g : Game, st : Step] {
	/*
	 * Preconditions
	 */
	// Fill This In

	/**uncomment* let st' = next[st] { **/
		/*
		 * Effects
		 */
		// Fill This In

		/*
		 * Frame
		 */
		// Fill This In
	/**uncomment* } **/
}

assert cancelGame_closed {
	all st : Step, g : Game {
		Invariant[st] && cancelGame[g, st] =>
			Invariant[next[st]]
	}
}
check cancelGame_closed for 6 but 2 Step

pred cancelGame_exists {
	some st : Step - last, g : Game {
		Invariant[st]
		cancelGame[g, st]
	}
}
run cancelGame_exists for 4 but 2 Step

/************* Reschedule a Game *************/

/**
 * Reschedule (if this will not cause a conflict) a currently scheduled
 * game 'g' on its current date but on field 'f':
 *		- The game is currently scheduled and assigned a field and date
 *		- No other games are assigned the field 'f' on game's 'g' current date.
 *		- The operation completes with the game being assigned the field requested.
 *		- The game's originally assigned date remains the same.
 *		- Nothing else about the schedule or the other games is affected.
 */
pred rescheduleGame[g : Game, f : Field, st : Step] {
	/*
	 * Preconditions
	 */
	// Fill This In

	/**uncomment* let st' = next[st] { **/
		/*
		 * Effects
		 */
		// Fill This In

		/*
		 * Frame
		 */
		// Fill This In
	/**uncomment* } */
}

assert rescheduleGame_closed {
	all st : Step, g : Game, f : Field {
		Invariant[st] && rescheduleGame[g, f, st] =>
			Invariant[next[st]]
	}
}
check rescheduleGame_closed for 6 but 2 Step

pred rescheduleGame_exists {
	some st : Step - last, g : Game, f : Field {
		Invariant[st]
		rescheduleGame[g, f, st]
	}
}
run rescheduleGame_exists for 4 but 2 Step



