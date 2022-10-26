import asyncio
import time

import jpyutil
jpyutil.init_jvm(jvm_options=["-Djava.rmi.server.hostname=localhost"], jvm_classpath=["target/classes"])
import jpy
App = jpy.get_type("io.github.evacchi.App")
app = App()


async def nextEl():
    return app.blocking_queue().take()


async def main():
    app.start()
    print("start")
    r = None
    r = await nextEl()
    print("awaited")
    print(r)

print("test")
asyncio.run(main())

print("---------------------")

app2 = App()
app2.start()
while True:
    r = app2.queue().poll()
    if r is not None:
        print(r)
        break
