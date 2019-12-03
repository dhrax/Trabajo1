package com.daisa.trabajo1;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ListaVideojuegosAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[] {"plataforma", "Tienda", "Genero", "Desarrolladora"};

    public ListaVideojuegosAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return PlataformaFragment.newInstance(position + 1);
        else if(position == 1)
            return TiendaFragment.newInstance(position + 1);
        else if(position==3)
            return GeneroFragment.newInstance(position + 1);
        else
            return DesarrolladoraFragment.newInstance(position + 1);
    }


    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
