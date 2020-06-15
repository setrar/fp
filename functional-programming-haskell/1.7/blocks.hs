-- roots 1 0 (-3)

roots a b c =
   let
      det2 = b*b-4*a*c;
      det  = sqrt(det2);
      rootp = (-b + det)/a/2;
      rootm = (-b - det)/a/2;
   in
      [rootp,rootm]
