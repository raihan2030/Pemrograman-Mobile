data class Barang(val id: String, var namaBarang: String, var stok: Int)

val listBarang = mutableListOf<Barang>()

fun addData(id: String, namaBarang: String, stok: Int){
    Barang(id, namaBarang, stok).also {
        listBarang.add(it)
        println("$it berhasil ditambahkan")
    }
}

fun showData(){
    println("== LIST DATA ==")
    if(listBarang.isEmpty()){
        println("List barang masih kosong")
    }
    else{
        var i = 0
        listBarang.forEach { barang ->
            i++
            println("$i | ID: ${barang.id}, Nama barang: ${barang.namaBarang}, Stok: ${barang.stok}")
        }
    }
}

fun editData(id: String){
    println("== DATA BARU ==")
    var namaBarang: String
    do {
        print("Nama Barang: ")
        namaBarang = readlnOrNull()?.trim() ?: ""
        if (namaBarang.isEmpty()) {
            println("Nama Barang tidak boleh kosong")
            continue
        }
        break
    } while(true)

    var stok: Int
    do {
        print("Stok: ")
        stok = readlnOrNull()?.trim()?.toIntOrNull() ?: -1
        if (stok < 0) {
            println("Input stok harus berupa angka positif")
        }
    } while(stok < 0)

    listBarang.find { it.id == id }?.apply {
        this.namaBarang = namaBarang
        this.stok = stok
    }

    println("Data barang dengan ID: $id berhasil diedit")
}

fun deleteData(id: String){
    val barang = listBarang.find { it.id == id }
    if(barang != null){
        listBarang.remove(barang)
        println("Data barang dengan ID: $id berhasil dihapus")
    }
    else{
        println("Barang dengan ID: $id tidak ditemukan")
    }
}

fun main() {
    var pilih: Int?

    do {
        println("\nDaftar Menu")
        println("1. Tambah Data")
        println("2. List Data")
        println("3. Edit Data")
        println("4. Hapus Data")
        println("5. Keluar")
        print("Pilihan: ")
        pilih = readlnOrNull()?.toIntOrNull()

        when (pilih) {
            1 -> {
                var id: String
                do {
                    print("ID: ")
                    id = readlnOrNull()?.trim() ?: ""
                    val cekId = listBarang.find { it.id == id }
                    if(id.isEmpty()){
                        println("ID tidak boleh kosong")
                        continue
                    }
                    else if(cekId != null) {
                        println("ID sudah ada")
                        continue
                    }
                    break

                } while(true)

                var namaBarang: String
                do {
                    print("Nama barang: ")
                    namaBarang = readlnOrNull()?.trim() ?: ""
                    if(namaBarang.isEmpty()){
                        println("Nama barang tidak boleh kosong")
                        continue
                    }
                    break
                } while(true)

                var stok: Int
                do {
                    print("Stok: ")
                    val inpStok = readlnOrNull()?.trim()
                    stok = inpStok?.toIntOrNull() ?: -1

                    if(stok < 0){
                        println("Input stok harus berupa angka positif")
                    }

                } while(stok < 0)

                addData(id, namaBarang, stok)
            }
            2 -> {
                showData()
            }
            3 -> {
                showData()

                if(listBarang.isNotEmpty()) {
                    var inpId: String
                    do {
                        print("Masukkan ID Barang yang ingin diedit: ")
                        inpId = readlnOrNull()?.trim() ?: ""
                        if (inpId.isEmpty()) {
                            println("ID Barang tidak boleh kosong")
                            continue
                        }

                        val barang = listBarang.find { it.id == inpId }
                        if (barang == null) {
                            println("ID Barang tidak ditemukan")
                            continue
                        }
                        break
                    } while (true)

                    editData(inpId)
                }
            }
            4 -> {
                showData()

                if(listBarang.isNotEmpty()) {
                    print("Masukkan ID data yang ingin dihapus: ")
                    val id = readlnOrNull()?.trim() ?: ""
                    deleteData(id)
                }
            }
            5 -> {
                println("Keluar program")
            }
            else -> {
                println("Angka tidak valid")
            }
        }

        print("Tekan untuk lanjut...")
        readln()


    }while (pilih != 5)

    println("Terima kasih sudah menggunakan program ini")
}