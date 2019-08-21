addressLetter name location = locationFunction name
  where locationFunction = getLocationFunction location

-- function 1
sfOffice name = if lastName < "L"
                then nameText
                     ++ " - PO Box 1234 - San Francisco, CA, 94111"
                else nameText
                     ++ " - PO Box 1010 - San Francisco, CA, 94109"
  where lastName = snd name
        nameText = (fst name) ++ " " ++ lastName

-- function 2
nyOffice name = nameText ++ ": PO Box 789 - New York, NY, 10013"
  where nameText = (fst name) ++ " " ++ (snd name)

-- function 3
renoOffice name = nameText ++ " - PO Box 456 - Reno, NV 89523"
  where nameText = snd name

-- function 4
dcOffice name = nameText ++ " - 1600 Avenue of the americas - Washington DC, DC 00000"
  where nameText = (snd name) ++ " Esq."



-- function returning a function (see above functions or a lambda function)
getLocationFunction location = case location of
  "ny" -> nyOffice
  "sf" -> sfOffice
  "reno" -> renoOffice
  "dc" -> dcOffice
  _ -> (\name -> (fst name) ++ " " ++ (snd name))


-- GHCi> addressLetter ("Bob","Smith") "ny"
-- GHCi> addressLetter ("Bob","Jones") "ny"
-- GHCi> addressLetter ("Samantha","Smith") "sf"
-- GHCi> addressLetter ("Samantha","Smith") "dc"

