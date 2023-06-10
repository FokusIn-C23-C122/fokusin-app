package com.capstone.fokusin.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.capstone.fokusin.R
class SettingFragment : Fragment() {

    private lateinit var profileImage: ImageView
    private lateinit var textName: TextView
    private lateinit var textEmail: TextView
    private lateinit var btnEditProfile: Button
    private lateinit var menuList: ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        // Inisialisasi komponen UI
        profileImage = view.findViewById(R.id.profile_image)
        textName = view.findViewById(R.id.tv_name)
        textEmail = view.findViewById(R.id.tv_email)
        btnEditProfile = view.findViewById(R.id.btn_edit_profile)
        menuList = view.findViewById(R.id.menu_list)

        // Mengatur data profil pengguna
        val user = getCurrentUser() // Fungsi untuk mendapatkan data pengguna
        profileImage.setImageResource(user.profileImage)
        textName.text = user.name
        textEmail.text = user.email

        // Mengatur aksi tombol edit profil
        btnEditProfile.setOnClickListener {
            // Tambahkan kode untuk membuka layar edit profil
            // Misalnya: val intent = Intent(requireContext(), EditProfileActivity::class.java)
            // startActivity(intent)
        }

        // Mengatur daftar menu
        val menuItems = arrayOf("Nigh Mode","Bantuan", "Logout")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, menuItems)
        menuList.adapter = adapter
        menuList.setOnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> {
                    // Aksi saat menu Bantuan diklik
                }
                1 -> {
                    // Aksi saat menu Logout diklik
                }
            }
        }

        return view
    }

    // Fungsi contoh untuk mendapatkan data pengguna
    private fun getCurrentUser(): User {
        // Gantikan dengan kode untuk mengambil data pengguna dari sumber data (misalnya database atau shared preferences)
        return User("John Doe", "john.doe@example.com", R.drawable.propil)
    }

    data class User(val name: String, val email: String, val profileImage: Int)
}
