package com.capstone.fokusin.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.fokusin.R
import com.capstone.fokusin.ui.activity.LoginScreen
import com.capstone.fokusin.ui.adapter.CustomListAdapter

class SettingFragment : Fragment(), CustomListAdapter.ItemClickListener {

    private lateinit var profileImage: ImageView
    private lateinit var textName: TextView
    private lateinit var textEmail: TextView
    private lateinit var menuList: ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        // Inisialisasi komponen UI
        profileImage = view.findViewById(R.id.iv_prof)
        textName = view.findViewById(R.id.tv_name)
        textEmail = view.findViewById(R.id.tv_email)
//        btnEditProfile = view.findViewById(R.id.btn_edit_profile)
//        menuList = view.findViewById(R.id.menu_list)


        // Mengatur data profil pengguna
        val user = getCurrentUser() // Fungsi untuk mendapatkan data pengguna
        profileImage.setImageResource(user.profileImage)
        textName.text = user.name
        textEmail.text = user.email

        // Mengatur daftar menu
        val menuItems = arrayOf("Nigh Mode", "Bantuan", "Logout")
//        val adapter = ArrayAdapter(requireContext(), R.layout.list_item_menu, menuItems)
        val adapter = CustomListAdapter(menuItems)
        val menuRecyclerView = view.findViewById<RecyclerView>(R.id.menuRecyclerView)
        adapter.setItemClickListener(this)
        menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        menuRecyclerView.adapter = adapter


        return view
    }

    // Fungsi contoh untuk mendapatkan data pengguna
    private fun getCurrentUser(): User {
        // Gantikan dengan kode untuk mengambil data pengguna dari sumber data (misalnya database atau shared preferences)
        return User("John Doe", "john.doe@example.com", R.drawable.propil)
    }

    data class User(val name: String, val email: String, val profileImage: Int)

    override fun onItemClick(position: Int) {
        when (position) {
            0 -> {
                // Aksi untuk menu item 1
//                findNavController().navigate(R.id.action_menu1)
                lodFragment(StatistikFragment())
            }
            1 -> {
                // Aksi untuk menu item 2
//                findNavController().navigate(R.id.action_menu2)
            }
            2 -> {
                // Aksi untuk menu item 3
//                findNavController().navigate(R.id.action_menu3)
                val intent = Intent(activity, LoginScreen::class.java)
                startActivity(intent)
            }
            // Tambahkan aksi navigasi untuk menu item lainnya
        }
    }
    private fun lodFragment(fragment: Fragment) {
        val trans = parentFragmentManager.beginTransaction()
        trans.replace(R.id.konten, fragment)
        trans.commit()
    }
}
