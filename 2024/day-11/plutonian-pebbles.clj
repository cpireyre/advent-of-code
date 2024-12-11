(defn parse [file]
  (->> file
      slurp
      (re-seq #"-?\d+")
      (map parse-long)))

; If the stone is engraved with the number 0, it is replaced by a stone engraved with the number 1.
; If the stone is engraved with a number that has an even number of digits, it is replaced by two stones. The left half of the digits are engraved on the new left stone, and the right half of the digits are engraved on the new right stone. (The new numbers don't keep extra leading zeroes: 1000 would become stones 10 and 0.)
; If none of the other rules apply, the stone is replaced by a new stone; the old stone's number multiplied by 2024 is engraved on the new stone.

(defn replace-stone [stone]
  (let [digits (str stone)
        size (count digits)]
  (cond
        (zero? stone) '(1)
        (even? size) (let [[left right] (split-at (/ size 2) digits)]
                       (list (parse-long (apply str left))
                         (parse-long (apply str right))))
        :else (list (* 2024 stone)))))

(def blink (partial mapcat (memoize replace-stone)))

(defn solve [file]
  (->> file
      parse
      (iterate blink)
      (take 30)
      last
      count))

(def evolve-stone
  (memoize
    (fn [stone iterations]
      (if (zero? iterations)
        1
        (->> (replace-stone stone)
             (map #(evolve-stone % (dec iterations)))
             (reduce +))))))

(defn part-2 [file]
  (->> file
       parse
       (map #(evolve-stone % 75))
       (reduce +)))
      

; (println (time (solve "example.txt")))
; (println (time (solve "puzzle-input.txt")))
(println (time (part-2 "example.txt")))
(println (time (part-2 "puzzle-input.txt")))
; 2097446912 14168 4048 2 0 2 4 40 48 2024 40 48 80 96 2 8 6 7 6 0 3 2
; ((2097446912 14168 4048 2 0 2 4) (40 48 2024 40 48 80 96 2 8 6 7 6 0 3 2))
