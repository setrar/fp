// https://stackoverflow.com/questions/40697075/scala-how-do-i-extract-the-first-secound-third-and-fourth-byte-from-an-integer


val x = -1234567890

x & 0xFF

/**
For example

-1234567890 = 10110110 01101001 11111101 00101110

10110110 = 182

01101001 = 105

11111101 = 253

00101110 = 46
*/


Integer.parseInt("0110",2)

val a = Int.MaxValue

a.toByte

a.toByte << 5

a.toByte >> 5

a.toByte > 5

a.toByte >> 5

a.toByte >> -5

a.toByte << 16

a.toByte << 8

a.toByte << 24

a.toByte << 32

a.toByte << (8 + 8)

a.toByte << (8 + 8 + 8)

val b = 1

b.toByte << (8 + 8 + 8)

(b.toByte << (8 + 8 + 8)).toBinaryString

(b.toByte << (8 + 8 + 8)).toBinaryString.length

(b.toByte << (8 + 8 )).toBinaryString.length

(b.toByte << (8 + 8 )).toBinaryString

(b.toByte << 8  ).toBinaryString

(b.toByte >> 8  ).toBinaryString


a.toBinaryString
//res110: String = 1111111111111111111111111111111

(a.toByte >> (8  )).toBinaryString
//res111: String = 11111111111111111111111111111111

(a.toByte >> (8  )).toBinaryString.length
//res112: Int = 32

(a.toByte >> (8  )).toBinaryString
//res113: String = 11111111111111111111111111111111

(a.toByte >> (16  )).toBinaryString
//res114: String = 11111111111111111111111111111111

(a.toByte >>> (16  )).toBinaryString
//res115: String = 1111111111111111

(a.toByte >>> (16  )).toBinaryString.size
//res116: Int = 16

/*
(a.toByte <<< (16  )).toBinaryString.size
^
error: value <<< is not a member of Byte
*/

(a.toByte << (16  )).toBinaryString.size
//res118: Int = 32

(a.toByte << (16  )).toBinaryString.size
//res119: Int = 32

(a.toByte << (16  )).toBinaryString
//res120: String = 11111111111111110000000000000000

(a.toByte >>> (16  )).toBinaryString
//res121: String = 1111111111111111

(a.toByte >>> (16  ))
//res122: Int = 65535

(a.toByte << (16  ))
//res123: Int = -65536

(a.toByte << (16  )).sign
//sign   signum

/*
(a.toByte << (16  )).signn
^
error: value signn is not a member of Int
*/

(a.toByte << (16  )).sign
//res126: Int = -1

(a.toByte << (16  ))
//res127: Int = -65536

val d = 1
//a: Int = 1

(d.toByte << (16  ))
//res128: Int = 65536

(d.toByte << (16  )).toBinaryString
//res131: String = 10000000000000000

(d.toByte >> (16  )).toBinaryString
//res132: String = 0

(d.toByte >>> (16  )).toBinaryString
//res133: String = 0

(d.toByte << (32  )).toBinaryString
//res134: String = 1

val c = 60
//c: Int = 60

c.toBinaryString
//res136: String = 111100

s"${c.toBinaryString}"
//res137: String = 111100

s"16s ${c.toBinaryString}"
//res138: String = 16s 111100

s"16%s ${c.toBinaryString}"
//res139: String = 16%s 111100

s"16%s,${c.toBinaryString}"
//res140: String = 16%s,111100


f"${c.toBinaryString}%16s"
//res142: String = "          111100"

f"${c.toBinaryString}%16.0s"
//res145: String = "                "

f"${c.toBinaryString}%16s"
//res146: String = "          111100"


f"${c.toBinaryString}%16s"
//res148: String = "          111100"

f"00000000${c.toBinaryString}%16s"
//res149: String = 00000000          111100

f"0000000000000000${c.toBinaryString}%16s"
//res150: String = 0000000000000000          111100

f"${c.toBinaryString}%16s"
//res151: String = "          111100"

f"${c.toBinaryString}"
//res152: String = 111100

Integer.parseInt("10110010",2)
//res5: Int = 178

Integer.parseInt("0110",2)
//res6: Int = 6

def toBinary(i: Int, digits: Int = 8) =
  String.format("%" + digits + "s", i.toBinaryString).replace(' ', '0')

