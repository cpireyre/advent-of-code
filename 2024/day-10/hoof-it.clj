(require '[clojure.string :as s]
         '[clojure.pprint :refer [pprint]])

(defn dimensions [matrix] [(count matrix) (count (first matrix))])

(def N [-1 0])
(def S [1 0])
(def W [0 -1])
(def E [0 1])
(def headings [N S W E])

(defn parse [file]
  (->> file
       slurp
       s/split-lines
       (mapv #(mapv (comp parse-long str) %))))

(defn get-neighbors [[row col] [rows cols]]
  (for [[x y] headings
        :let [new-row (+ row x)
              new-col (+ col y)]
        :when (and (<= 0 new-row (dec rows))
                   (<= 0 new-col (dec cols)))]
    [new-row new-col]))

(defn trails [matrix size start]
  (let [height (fn [v] (get-in matrix v))
        curr-height (height start)
        neighbors (get-neighbors start size)
        paths (filter #(= (height %) (inc curr-height)) neighbors)]
    paths))

(defn bfs [matrix size start]
  (loop [seen #{}
         [h & t] #{start}]
    (cond
      (nil? h) seen
      (contains? seen h) (recur seen t)
      :else (recur (conj seen h) (into t (trails matrix size h))))))

(defn trailheads [matrix]
  (let [size (dimensions matrix)
        nine? (fn [v] (= (get-in matrix v) 9))]
    (for [x (range (first size))
          y (range (second size))
          :when (zero? (get-in matrix [x y]))]
      (->> [x y]
           (bfs matrix size)
           (filter nine?)))))

(defn part-1 [file]
  (let [matrix (parse file)]
    (->> matrix
         trailheads
         (map count)
         (reduce +))))

(pprint (time (part-1 "puzzle-input.txt")))


(defn count-paths [matrix size start]
  (loop [seen []
         [h & t] #{start}]
    (if (nil? h)
      seen
      (recur (conj seen h) (into t (trails matrix size h))))))

; (defn dfs [matrix size pos seen]
;   (if (= 9 (get-in matrix pos))
;     1
;     (->> (trails matrix size pos)
;          (remove seen)
;          (map #(dfs matrix size % (conj seen pos)))
;          (reduce +))))

(defn trailheads-v2 [matrix]
  (let [size (dimensions matrix)
        nine? (fn [v] (= (get-in matrix v) 9))]
    (for [x (range (first size))
          y (range (second size))
          :when (zero? (get-in matrix [x y]))]
      (->> [x y]
           (count-paths matrix size)
           (filter nine?)))))

(defn part-2 [file]
  (let [matrix (parse file)]
    (->> matrix
         trailheads-v2
         (map count)
         (reduce +))))

(pprint (time (part-2 "puzzle-input.txt")))
(time (parse "puzzle-input.txt"))
