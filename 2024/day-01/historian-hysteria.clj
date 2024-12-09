; (def input (slurp "example.txt"))
(def input (slurp "puzzle-input.txt"))
(def lines (clojure.string/split-lines input))
(def pairs (map #(clojure.string/split % #"\s+") lines))

(defn parse-pair [[x y]]
  [(Integer/parseInt x) (Integer/parseInt y)])

(def number-pairs (map parse-pair pairs))
(def nums (apply map vector number-pairs))
(def xs (sort (first nums)))
(def ys (sort (second nums)))

(comment ; Part One:
         (def differences (map - xs ys))
         (def distances (map #(Math/abs %) differences))
         (def result (apply + distances))

         ; (prn xs)
         ; (prn ys)
         ; (prn distances)
         (prn result)
         )

(prn xs ys)

(def freqs (frequencies ys))
(prn freqs)

(defn score [freqs x]
  (if (freqs x)
    (* (freqs x) x)
    0))

(def scores (map #(score freqs %) xs))
(def result (apply + scores))
(prn result)
