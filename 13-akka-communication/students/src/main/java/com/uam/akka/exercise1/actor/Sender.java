package com.uam.akka.exercise1.actor;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Sender extends UntypedActor {

	private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	@Override
	public void onReceive(Object o) throws Exception {
	}
}
