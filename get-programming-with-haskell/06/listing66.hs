assignToGroups n aList = groups `zip` aList
   where groups = cycle [1..n]
