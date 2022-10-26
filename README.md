# Queue-Based Communication

- build the tiny Java library

      mvn package

- Start the Python example (install jpy)

      python src/main/python/app.py


The Java library provides access to a Java queue. 
There are two methods, `App#blocking_queue` and `App#queue`:

- `blocking_queue()` provides the `take()` method tha blocks the callee. 
  It is meant to be used e.g. via `asyncio`

- `queue()` provides the `poll()` method that returns `None` when the queue is empty, without blocking. 
  It can be safely used anywhere because it won't block the calling thread.


