module Chapter1 exposing (..)

pluralize singular plural count = 
    if count == 1 then singular else plural

-- Running

-- import Chapter1 exposing (..) 

-- pluralize "elf" "elves" 3
-- pluralize "elf" "elves" (round 0.9)


dash = '-'
isKeepable character = character /= dash

-- isKeepable 'z'
-- isKeepable '-'
-- String.filter isKeepable "800-555-1234"

withoutDashes str = String.filter isKeepable str


multiply3d ( x, y, z ) = x * y * z

-- > multiply3d ( 6, 7, 2 )

multiply2d someTuple = let ( x, y ) = someTuple in x * y




