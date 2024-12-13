(require '[clojure.string :as s])
(require '[clojure.math :as math])
(require '[clojure.pprint :refer [pprint]])
(require '[clojure.core.matrix :as m])
(require '[clojure.core.matrix.linear :as l])
(m/set-current-implementation :vectorz)

(def nums #(mapv parse-long (re-seq #"\d+" %)))

(defn parse-game [[ax ay bx by px py]]
  {:coefficients (m/array [[ax bx] [ay by]])
   :constants (m/array [px py])})

(defn parse-game-2 [[ax ay bx by px py]]
  {:coefficients (m/array [[ax bx] [ay by]])
   :constants (m/array [(+ 10000000000000 px) (+ 10000000000000 py)])})

(defn parse [file]
  (->> file
       slurp
       (#(s/split % #"\n\n"))
       (map nums)
       (map parse-game-2)))

(defn price [[a b]]
  {:a a :b b :price (+ (* 3 a) b)})

(defn round? [x]
  (let [epsilon 0.0001]
    (<= (abs (- x (math/round x))) epsilon )))

(defn solve [{:keys [coefficients constants]}]
  (let [solution (l/solve coefficients constants)]
    (when (and (every? round? solution)
               (every? pos? solution)
               (every? #(<= % 100) solution))
      (mapv math/round solution))))

(defn solve-2 [{:keys [coefficients constants]}]
  (let [solution (l/solve coefficients constants)]
    (when (and (every? round? solution)
               (every? pos? solution))
      (mapv math/round solution))))


(defn part-1 [systems]
  (->> systems
       (map solve)
       (remove nil?)
       (map price)
       (map :price)
       (reduce +)))

(defn part-2 [systems]
  (->> systems
       (map solve-2)
       (remove nil?)
       (map price)
       (map :price)
       (reduce +)))

; (pprint (part-1 (parse "example.txt")))
; (pprint (time (part-1 (parse "puzzle-input.txt"))))
(pprint (time (part-2 (parse "example.txt"))))
(pprint (time (part-2 (parse "puzzle-input.txt"))))
