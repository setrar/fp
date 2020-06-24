module SomeModule exposing (pluralize)

pluralize singular plural count = 
    if count == 1 then singular else plural

--> import SomeModule exposing (pluralize) 
--> pluralize "elf" "elves" 3

-- pluralize "elf" "elves" (round 0.9)


