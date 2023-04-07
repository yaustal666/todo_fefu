package com.example.todo_fefu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.todo_fefu.fragments.list.ListsFragmentDirections
import com.example.todo_fefu.fragments.subtask.SubtasksFragmentArgs
import com.example.todo_fefu.fragments.task.TaskFragmentArgs

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.fragment))
    }

    override fun onSupportNavigateUp(): Boolean {

        val navController = findNavController(R.id.fragment)



        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.app_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.to_lists_button -> {
                findNavController(R.id.fragment).navigate(R.id.listsFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}