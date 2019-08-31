import Data.List

names = [("Ian", "Curtis"),
         ("Bernard","Sumner"),
         ("Peter", "Hook"),
         ("Stephen","Morris")]

compareLastNames name1 name2 = compare lastName1 lastName2
  where lastName1 = snd name1
        lastName2 = snd name2

-- Prolog> sortBy compareLastNames names
