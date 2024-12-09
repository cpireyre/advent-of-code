(require '[clojure.core.matrix :as m]
         '[clojure.math.combinatorics :as combo])

(defn parse [file]
  (let [grid (->> file
                  slurp
                  clojure.string/split-lines
                  (map vec)
                  m/array)]
    grid))

(defn find-antennae [matrix]
  (let [indices (m/index-seq matrix)
        elements (m/eseq matrix)]
    (map second
         (reduce
           (fn [acc [val idx]]
             (if (not= val \.)
               (update acc val (fnil conj []) idx)
               acc))
           {}
           (map vector elements indices)))))

(defn antinode [[a b]]
  (let [d (m/sub b a)]
    [(m/add b d) (m/sub a d)]))


(defn in-bounds? [grid [y x]]
  (and (< -1 y (m/row-count grid))
       (< -1 x (m/column-count grid))))

(defn resonant-antinodes [grid [a b]]
  (let [d (m/sub b a)
        up (fn [v]
             (m/add v d))
        down (fn [v]
               (m/sub v d))]
     (concat
       (take-while #(in-bounds? grid %) (iterate up b))
     (take-while #(in-bounds? grid %) (iterate down a)))
     ))

(defn find-antinodes [grid]
  (->> grid
       find-antennae
       (mapcat #(combo/combinations % 2))
       (mapcat antinode)
       distinct
       (filter #(in-bounds? grid %))))

(defn find-resonant-antinodes [grid]
  (->> grid
       find-antennae
       (mapcat #(combo/combinations % 2))
       (mapcat #(resonant-antinodes grid %))))

(defn part-1 [file]
  (->> file
       parse
       find-antinodes
       count))

(defn part-2 [file]
  (->> file
       parse
       find-resonant-antinodes
       distinct
       count))

(println (time (part-1 "example.txt")))
(println (time (part-1 "puzzle-input.txt")))
(println (time (part-2 "example.txt")))
(println (time (part-2 "puzzle-input.txt")))
