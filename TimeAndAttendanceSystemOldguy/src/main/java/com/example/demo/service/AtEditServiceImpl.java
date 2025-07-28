package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.EmpAtData;
import com.example.demo.repository.EmpAtDataRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AtEditServiceImpl implements AtEditService {

	private final EmpAtDataRepository repository;

	@Override
	public void edit(EmpAtData ead) {

		repository.update(ead);

	}

}
