(require '[clojure.pprint :refer [pprint]])

; (def file "example.txt")
(def file "puzzle-input.txt")

(def grid
  (->> (slurp file)
       (clojure.string/split-lines)
       (map vec)
       (vec)))

(def size [(count grid) (count (first grid))])
(def rows (first size))
(def cols (second size))
(def bars (map #(apply str %) (for [i (range cols)] (map #(nth % i) grid))))

(pprint grid)
(pprint size)

(defn xmas [show]
  (+ (count (re-seq #"XMAS" show))
     (count (re-seq #"SAMX" show))))

(defn scan [row]
  (xmas (apply str row)))

(defn find [grid]
  (apply + (map #(scan (nth grid %)) (range (count grid)))))

; (pprint {:hori (find grid) :vert (find bars)})

; (pprint (get-in grid [2 2]))
; (pprint (range 4))

(defn look [grid [orix oriy]]
  (let [east (for [i (range 4)] [(+ orix i) (+ oriy i)])
        west (for [i (range 4)] [(+ orix i) (- oriy i)])]
    (+ (scan (map #(get-in grid %) east))
       (scan (map #(get-in grid %) west)))))

(def cels
  (for [x (range rows)
        y (range cols)]
    [x y]))

; part one:
; (def diag (map #(look grid %) cels))
; (def okok (+ (find grid)
;              (find bars)
;              (apply + diag)))
; (prn okok)

; part two

(defn getx [grid [orix oriy]]
  (let [rise (for [i (range -1 2)] [(+ orix i) (+ oriy i)])
        fall (for [i (range -1 2)] [(+ orix i) (- oriy i)])]
  [(map #(get-in grid %) rise) 
   (map #(get-in grid %) fall)]))

(defn mas? [cross]
  (let [rise (apply str (first cross))
        fall (apply str (second cross))]
    (and (or (= rise "SAM") (= rise "MAS"))
         (or (= fall "SAM") (= fall "MAS")))))

(count (remove false? (map #(mas? (getx grid %)) cels)))
; (pprint (map #(getx grid %) cels))
