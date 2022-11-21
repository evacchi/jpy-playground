import asyncio
import time
import os, tempfile

import jpyutil
jpyutil.init_jvm(jvm_options=["-Djava.rmi.server.hostname=localhost"], jvm_classpath=["target/classes"])
import jpy
App = jpy.get_type("io.github.evacchi.App")
NamedPipe = jpy.get_type("io.github.evacchi.NamedPipe")

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

print("---------------------")
tmpdir = tempfile.mkdtemp()
filename = os.path.join(tmpdir, 'myfifo')
print(filename)
os.mkfifo(filename)
app3 = NamedPipe(filename)
app3.start()
with open(filename) as fifo:
    print("FIFO opened")
    data = None
    count = 0
    while count < 2:
        data = fifo.readline()
        count += 1
        print('Read: "{0}"'.format(data))
